package peer_review.models;

import java.util.List;
import java.util.Optional;

public class Article {
	private int id;
	private String title;
	private Researcher author;
	private List<Researcher> reviewers;
	private Conference conference;
	private ResearchTopic researchTopic;
	private List<Grade> grades;

	public Article(int id, String title, Researcher author, List<Researcher> reviewers, Conference conference,
			ResearchTopic researchTopic, List<Grade> grades) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.reviewers = reviewers;
		this.conference = conference;
		this.researchTopic = researchTopic;
		this.grades = grades;
		conference.addArticlesSubmitted(this);
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(Researcher author) {
		this.author = author;
	}

	public void setGrade(Researcher researcher, Optional<Float> grade) {
		grades.add(new Grade(researcher, grade));
		conference.addArticlesAllocatted(this);
	}

	public University getAuthorUniversity() {
		return author.getUniversity();
	}
	
	public int getID() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Researcher getAuthor() {
		return author;
	}

	public ResearchTopic getResearchTopic() {
		return researchTopic;
	}
	
	public List<Grade> getGrades() {
		return grades;
	}

	public List<Researcher> getReviewers() {
		return reviewers;
	}

	public Boolean isResearcherAllocated(Researcher researcher) {
		return reviewers.contains(researcher);
	}

	public int numberOfReviewers() {
		return reviewers.size();
	}
	
	public void allocateReviewer(Researcher reviewer) {
		reviewers.add(reviewer);
	}

	public float getGradeAverage() {
		return (float) grades.stream().
				mapToDouble((grade) -> grade.getGrade().get()).average().getAsDouble();
	}

	public String toStringSimple() {
		return "ID:" + getID() + " Article:" + getTitle();
	}

	@Override
	public String toString() {
		String result = toStringSimple() + "\n";
		result += "Author:\n" + author.toStringSimple() + "\n";
		result += "Reviewers:\n";
		for (Researcher reviewer : reviewers) {
			result += reviewer.toStringSimple() + "\n";
		}

		result += "Conference:\n" + conference.toStringSimple() + "\n";
		result += "Research topic:\n" + researchTopic.toString() + "\n";
		return result;
	}

}
