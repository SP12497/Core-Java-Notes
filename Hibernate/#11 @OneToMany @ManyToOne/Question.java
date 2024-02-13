package com.QueAns;

import java.util.List;

import javax.persistence.*;

@Entity
public class Question
{
	@Id
	private int questionId;
	private String question;
	
	//@OneToMany	//One questionhas many answer
	@OneToMany(mappedBy = "question") 	//member of Answer
	private List <Answer> list ;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Answer> getList() {
		return list;
	}

	public void setList(List<Answer> list) {
		this.list = list;
	}

	public Question() {
		super();
		
	}

	public Question(int questionId, String question, List<Answer> list) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.list = list;
	}
	
	
}
