package com.howtodoinjava.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.howtodoinjava.demo.model.DBFile;

@Mapper
public interface DBFileStorageRepository {
	@Insert("INSERT into ${tableName}(A, B, C, D, E, F, G, H, I) VALUES(#{e.A},#{e.B},#{e.C},#{e.D},#{e.E},#{e.F},#{e.G},#{e.H},#{e.I})")
	public boolean insertUser(DBFile e, String tableName);

	public void createTable(@Param("tableName") String tableName);
}
