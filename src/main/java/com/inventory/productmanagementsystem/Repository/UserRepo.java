package com.inventory.productmanagementsystem.Repository;

import com.inventory.productmanagementsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long > {

}
