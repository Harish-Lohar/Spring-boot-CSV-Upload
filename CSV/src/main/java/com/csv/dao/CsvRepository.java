package com.csv.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csv.model.Items;

public interface CsvRepository extends JpaRepository<Items,Long> {

}
