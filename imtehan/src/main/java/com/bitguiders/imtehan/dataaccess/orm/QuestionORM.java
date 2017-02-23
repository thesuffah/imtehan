package com.bitguiders.imtehan.dataaccess.orm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionORM implements Serializable{

	private int questionId;
	private int questionBundleId;
	private String question;
	private String type;
	private boolean isTru;
	private boolean isFals;
	private String options;
	private List<OptionORM> optionsList= new ArrayList<OptionORM>();
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getQuestionBundleId() {
		return questionBundleId;
	}
	public void setQuestionBundleId(int questionBundleId) {
		this.questionBundleId = questionBundleId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isTru() {
		return isTru;
	}
	public void setTru(boolean isTru) {
		this.isTru = isTru;
	}
	public boolean isFals() {
		return isFals;
	}
	public void setFals(boolean isFals) {
		this.isFals = isFals;
	}
	public String getOptions() {
		options="";
		int total = optionsList.size();
		for(OptionORM o:optionsList){
			if(o.getOptionLabel().trim().length()>0){
			options += o.getOptionLabel()+"op~"+o.isCorrect()+(--total!=0?",":"");
			}
		}
		return options;
	}
	public boolean getUserAnswer(){
		boolean isCorrect = true;
		for(OptionORM o:optionsList){
			if(o.isCorrect()!=o.isUserAnswer()){
				return false;
			}
		}
		return isCorrect;
	}
	public String getUserAnswers(){
		String answers="";
		int total = optionsList.size();
		for(OptionORM o:optionsList){
			if(o.getOptionLabel().trim().length()>0){
			answers += o.isUserAnswer()+(--total!=0?",":"");
			}
		}
		return answers;
	}
	public String getCorrectAnswers(){
		String answers="";
		int total = optionsList.size();
		for(OptionORM o:optionsList){
			if(o.getOptionLabel().trim().length()>0){
			answers += o.isCorrect()+(--total!=0?",":"");
			}
		}
		return answers;
	}
	public void setOptions(String options) {
		this.options = options;
		if(null!=options && options.contains(",")){
			String[] option = options.split(",");
			optionsList = new ArrayList<OptionORM>();
			for(String o:option){
				if(o.contains("op~")){
				String choices[] = o.split("op~");
				optionsList.add(new OptionORM(choices[0], Boolean.parseBoolean(choices[1])));
				}else{
					optionsList.add(new OptionORM(o, false));
				}
			}
		}
	}

	public List<OptionORM> getOptionsList() {
		if(optionsList.size()==0 && null!=options && options.contains(",")){
			setOptions(options);
		}
		return optionsList;
	}
	public void setOptionsList(List<OptionORM> optionsList) {
		this.optionsList = optionsList;
	}

}
