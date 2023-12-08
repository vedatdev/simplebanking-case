package com.eteration.simplebanking.mapper;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.dto.AccountResponse;
import com.eteration.simplebanking.model.dto.TransactionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestResponseMapper {
    AccountResponse accountToResponse(Account account);

    TransactionDto transactionToDto(Transaction transaction);
}
