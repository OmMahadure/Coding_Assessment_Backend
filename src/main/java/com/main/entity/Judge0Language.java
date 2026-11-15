package com.main.entity;


public enum Judge0Language {
	
	    CPP(ProgrammingLanguage.CPP, 105),
	    JAVA(ProgrammingLanguage.JAVA, 91),
	    PYTHON(ProgrammingLanguage.PYTHON, 109);

	    private  ProgrammingLanguage language ;
	    private  int languageId;

	    Judge0Language(ProgrammingLanguage language, int languageId) {
	        this.language = language;
	        this.languageId = languageId;
	    }

	    public int getLanguageId() {
	        return languageId;
	    }

	    public static int getId(ProgrammingLanguage language) {
	        for (Judge0Language item : values()) {
	            if (item.language == language) {
	                return item.languageId;
	            }
	        }

	        throw new IllegalArgumentException("Unsupported language");
	    }
	}

