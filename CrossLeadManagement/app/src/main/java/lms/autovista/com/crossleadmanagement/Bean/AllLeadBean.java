package lms.autovista.com.crossleadmanagement.Bean;

public class AllLeadBean {

    private String question;
    private String answers;

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public AllLeadBean(String question, String answers) {
        super();
        this.question = question;
        this.answers = answers;
    }

    public AllLeadBean(){

    }
}
