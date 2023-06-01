package com.learning.splitwise.splitwise.service.settleupstrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.learning.splitwise.splitwise.dto.Transaction;
import com.learning.splitwise.splitwise.dto.UserExpensePair;
import com.learning.splitwise.splitwise.model.ExpenseOwingUser;
import com.learning.splitwise.splitwise.model.ExpensePayingUser;
import com.learning.splitwise.splitwise.model.User;

public class MinMaxSettleUpStrategy implements SettleUpTransactionCalculatorStrategy {

	@Override
	public List<Transaction> getTransactions(List<ExpenseOwingUser> expenseOwingUsers,
			List<ExpensePayingUser> expensePayingUsers) {

		Map<User, Double> map = new HashMap<>();
		List<Transaction> transactions = new ArrayList<>();

		// Calculating the total borrowed or lended amount for each user. Map will store
		// the same.

		// update(represent) the paying users with positive value
		for (ExpensePayingUser expensePayingUser : expensePayingUsers) {
			if (map.containsKey(expensePayingUser.getUser())) {
				double currentAmount = map.get(expensePayingUser.getUser());
				double updatedAmount = currentAmount + expensePayingUser.getAmount();
				map.put(expensePayingUser.getUser(), updatedAmount);
			} else {
				map.put(expensePayingUser.getUser(), expensePayingUser.getAmount());
			}
		}

		// update(represent) the owing users with negative value
		for (ExpenseOwingUser expenseOwingUser : expenseOwingUsers) {
			if (map.containsKey(expenseOwingUser.getUser())) {
				double currentAmount = map.get(expenseOwingUser.getUser());
				double updatedAmount = currentAmount - expenseOwingUser.getAmount();
				map.put(expenseOwingUser.getUser(), updatedAmount);
			} else {
				map.put(expenseOwingUser.getUser(), -1 * expenseOwingUser.getAmount());
			}
		}

		PriorityQueue<UserExpensePair> borrowersMinHeap = new PriorityQueue<>(
				Comparator.comparingDouble(UserExpensePair::getAmount));
		PriorityQueue<UserExpensePair> lendorsMaxHeap = new PriorityQueue<>(
				Comparator.comparingDouble(UserExpensePair::getAmount).reversed());

		for (Map.Entry<User, Double> entry : map.entrySet()) {
			if (entry.getValue() < 0)
				borrowersMinHeap.add(new UserExpensePair(entry.getKey(), entry.getValue()));
			else if (entry.getValue() > 0)
				lendorsMaxHeap.add(new UserExpensePair(entry.getKey(), entry.getValue()));
		}
		
		while(!borrowersMinHeap.isEmpty()) {
			UserExpensePair borrowerPair = borrowersMinHeap.poll();
			double borrowedAmount = borrowerPair.getAmount();
			
			UserExpensePair lenderPair = lendorsMaxHeap.poll();
			User lender = lenderPair.getUser();
			double lendAmount = lenderPair.getAmount();
			
			if(Math.abs(borrowedAmount) > lendAmount) {
				borrowerPair.setAmount(borrowedAmount + lendAmount);
				borrowersMinHeap.add(borrowerPair);
				transactions.add(new Transaction(borrowerPair.getUser(), lenderPair.getUser(), lendAmount));
			}else if(Math.abs(borrowedAmount) < lendAmount) {
				lenderPair.setAmount(lendAmount - borrowedAmount);
				lendorsMaxHeap.add(lenderPair);
				transactions.add(new Transaction(borrowerPair.getUser(), lenderPair.getUser(), borrowedAmount));
			}else {
				transactions.add(new Transaction(borrowerPair.getUser(), lenderPair.getUser(), lendAmount));
			}
			
		}
		
		return transactions;
	}

}
