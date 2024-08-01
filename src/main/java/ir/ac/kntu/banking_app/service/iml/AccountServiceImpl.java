package ir.ac.kntu.banking_app.service.iml;

import ir.ac.kntu.banking_app.dto.AccountDto;
import ir.ac.kntu.banking_app.entity.Account;
import ir.ac.kntu.banking_app.mapper.AccountMapper;
import ir.ac.kntu.banking_app.repository.AccountRepository;
import ir.ac.kntu.banking_app.service.AccountService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
	private final AccountRepository accountRepository;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.maptoAccount(accountDto);
		accountRepository.save(account);
		return AccountMapper.maptoAccountDto(account);
	}

	@SneakyThrows
	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
		return AccountMapper.maptoAccountDto(account);
	}

	@SneakyThrows
	@Override
	public AccountDto deposit(Long id, Double amount) {
		Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
		account.setBalance(account.getBalance() + amount);
		Account savedAccount =  accountRepository.save(account);
		return AccountMapper.maptoAccountDto(savedAccount);
	}

	@SneakyThrows
	@Override
	public AccountDto withdraw(Long id, Double amount) {
		Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
		if (account.getBalance() < amount) {
			throw new RuntimeException("Insufficient balance");
		}
		account.setBalance(account.getBalance() - amount);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.maptoAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map(AccountMapper::maptoAccountDto).toList();
	}

	@SneakyThrows
	@Override
	public void deleteAccount(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
		accountRepository.deleteById(id);
	}
}
