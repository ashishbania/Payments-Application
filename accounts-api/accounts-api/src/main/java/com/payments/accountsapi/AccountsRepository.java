package com.payments.accountsapi;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Integer> {
  @Override
  Optional<Accounts> findById(Integer id);

  @Override
  List<Accounts> findAll();

  @Override
  void deleteById(Integer integer);
}
