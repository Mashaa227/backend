package com.mmba.loans.service.impl;

import com.mmba.loans.constant.LoansConstants;
import com.mmba.loans.dto.LoanDto;
import com.mmba.loans.entity.Loan;
import com.mmba.loans.exception.LoanAlreadyExistsException;
import com.mmba.loans.exception.ResourceNotFoundException;
import com.mmba.loans.mapper.LoanMapper;
import com.mmba.loans.repository.LoansRepository;
import com.mmba.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createLoan(String mobileNumber) {

        Optional<Loan> byMobileNumber = loansRepository.findByMobileNumber(mobileNumber);
        if (byMobileNumber.isPresent()){
            throw new LoanAlreadyExistsException("loan is already resister to given mobile number"+mobileNumber);
        }

        loansRepository.save(createNewLoan(mobileNumber));
    }

    private Loan createNewLoan(String mobileNUmber){
        Loan loan = new Loan();
        Long  randomLoanNumber = 100000000000L+new Random().nextInt(900000000);
        loan.setLoanNumber(randomLoanNumber.toString());
        loan.setMobileNumber(mobileNUmber);
        loan.setLoanType(LoansConstants.HOME_LOAN);
        loan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loan.setAmountPaid(0);
        loan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return loan;
    }
    /**
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        Loan loan = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobile", mobileNumber));
        return LoanMapper.mapToLoansDto(loan,new LoanDto());
    }

    /**
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateLoan(LoanDto loansDto) {
        Loan loan = loansRepository.findByLoanNumber(loansDto.getLoanNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Loans", "LoanNumber", loansDto.getLoanNumber()));
        LoanMapper.mapToLoans(loansDto,loan);
        loansRepository.save(loan);
        return true;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of loan details is successful or not
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loan = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
        loansRepository.deleteById(loan.getLoanId());
        return true;
    }
}
