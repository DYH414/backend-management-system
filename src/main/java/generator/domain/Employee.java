package generator.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 员工表
* @TableName employee
*/
public class Employee implements Serializable {

    /**
    * ID
    */
    @NotNull(message="[ID]不能为空")
    @ApiModelProperty("ID")
    private Integer id;
    /**
    * 名称
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("名称")
    @Length(max= 255,message="编码长度不能超过255")
    private String name;
    /**
    * 性别
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("性别")
    @Length(max= 255,message="编码长度不能超过255")
    private String sex;
    /**
    * 工号
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("工号")
    @Length(max= 255,message="编码长度不能超过255")
    private String no;
    /**
    * 年龄
    */
    @ApiModelProperty("年龄")
    private Integer age;
    /**
    * 个人介绍
    */
    @Size(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty("个人介绍")
    @Length(max= -1,message="编码长度不能超过-1")
    private String description;
    /**
    * 部门
    */
    @ApiModelProperty("部门")
    private Integer departmentId;

    /**
    * ID
    */
    private void setId(Integer id){
    this.id = id;
    }

    /**
    * 名称
    */
    private void setName(String name){
    this.name = name;
    }

    /**
    * 性别
    */
    private void setSex(String sex){
    this.sex = sex;
    }

    /**
    * 工号
    */
    private void setNo(String no){
    this.no = no;
    }

    /**
    * 年龄
    */
    private void setAge(Integer age){
    this.age = age;
    }

    /**
    * 个人介绍
    */
    private void setDescription(String description){
    this.description = description;
    }

    /**
    * 部门
    */
    private void setDepartmentId(Integer departmentId){
    this.departmentId = departmentId;
    }


    /**
    * ID
    */
    private Integer getId(){
    return this.id;
    }

    /**
    * 名称
    */
    private String getName(){
    return this.name;
    }

    /**
    * 性别
    */
    private String getSex(){
    return this.sex;
    }

    /**
    * 工号
    */
    private String getNo(){
    return this.no;
    }

    /**
    * 年龄
    */
    private Integer getAge(){
    return this.age;
    }

    /**
    * 个人介绍
    */
    private String getDescription(){
    return this.description;
    }

    /**
    * 部门
    */
    private Integer getDepartmentId(){
    return this.departmentId;
    }

}
