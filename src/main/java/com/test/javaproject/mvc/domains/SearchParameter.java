package com.test.javaproject.mvc.domains;

/**
 * Created by Alexey on 08.08.2017.
 */
public class SearchParameter {

    //name or number
    private String searchBy;
    private String parameter;

    public SearchParameter() {
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return "SearchParameter{" +
                "searchBy='" + searchBy + '\'' +
                ", parameter='" + parameter + '\'' +
                '}';
    }
}
