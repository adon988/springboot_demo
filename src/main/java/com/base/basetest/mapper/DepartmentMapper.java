package com.base.basetest.mapper;

import com.base.basetest.models.Department;
import org.apache.ibatis.annotations.*;

//@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id") //自動生成id，可即時取回目前insert 的 id
    @Insert("insert into department(departmentName) value(#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    public int updateDept(Department department);
}
