package com.spring.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.entity.Student;

public class StudentDao_Impl implements StudentDao {

	private JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public int insert(Student student) {
		String sql = "INSERT INTO student(id,name,city) VALUES(?,?,?)";
		int insert = template.update(sql, student.getId(), student.getName(), student.getCity());

		return insert;
	}

	@Override
	public int update(Student student) {
		String sql = "UPDATE student SET name=? , city=? WHERE id = ?";
		int update = template.update(sql, student.getName(), student.getCity(), student.getId());
		return update;
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM student WHERE id=?";
		int delete = template.update(sql, id);
		return delete;
	}

	@Override
	public Student fetchStudent(int id) {
		String sql = "SELECT * FROM student WHERE id =?";
		RowMapper<Student> rowMapper = new RowMapperImpl();
		Student student = template.queryForObject(sql, rowMapper, id);
		return student;
	}

	@Override
	public List<Student> fetchAllStudents() {
		String sql = "SELECT * FROM student";
		RowMapper<Student> rowMapper = new RowMapperImpl();
		List<Student> students = template.query(sql, rowMapper);
		return students;
	}

}
