package com.inventory.productmanagementsystem.Repository;

import com.inventory.productmanagementsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long > {

}
