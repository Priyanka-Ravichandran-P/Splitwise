package com.learning.splitwise.splitwise.service.settleupstrategy;

import java.util.List;

import com.learning.splitwise.splitwise.dto.Transaction;
import com.learning.splitwise.splitwise.model.ExpenseOwingUser;
import com.learning.splitwise.splitwise.model.ExpensePayingUser;

public interface SettleUpTransactionCalculatorStrategy {
	List<Transaction> getTransactions(List<ExpenseOwingUser> expenseOwingUsers,
										List<ExpensePayingUser> expensePayingusers); 
}
