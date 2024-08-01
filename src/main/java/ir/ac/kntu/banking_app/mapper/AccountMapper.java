package ir.ac.kntu.banking_app.mapper;

import ir.ac.kntu.banking_app.dto.AccountDto;
import ir.ac.kntu.banking_app.entity.Account;

public class AccountMapper {
	public static Account maptoAccount(AccountDto accountDto) {
		return new Account(
				accountDto.getId(),
				accountDto.getAccountHolderName(),
				accountDto.getBalance()
		);
	}
	public static AccountDto maptoAccountDto(Account account) {
		return new AccountDto(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance()
		);
	}
}
