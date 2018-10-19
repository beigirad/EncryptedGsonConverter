package ir.beigirad.sample;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Movie{

	@SerializedName("Released")
	private String released;

	@SerializedName("Metascore")
	private String metascore;

	@SerializedName("imdbID")
	private String imdbID;

	@SerializedName("Plot")
	private String plot;

	@SerializedName("Images")
	private List<String> images;

	@SerializedName("Director")
	private String director;

	@SerializedName("Title")
	private String title;

	@SerializedName("Actors")
	private String actors;

	@SerializedName("imdbRating")
	private String imdbRating;

	@SerializedName("imdbVotes")
	private String imdbVotes;

	@SerializedName("Response")
	private String response;

	@SerializedName("Runtime")
	private String runtime;

	@SerializedName("Type")
	private String type;

	@SerializedName("Awards")
	private String awards;

	@SerializedName("Year")
	private String year;

	@SerializedName("Language")
	private String language;

	@SerializedName("Rated")
	private String rated;

	@SerializedName("Poster")
	private String poster;

	@SerializedName("Country")
	private String country;

	@SerializedName("Genre")
	private String genre;

	@SerializedName("Writer")
	private String writer;

	public String getReleased(){
		return released;
	}

	public String getMetascore(){
		return metascore;
	}

	public String getImdbID(){
		return imdbID;
	}

	public String getPlot(){
		return plot;
	}

	public List<String> getImages(){
		return images;
	}

	public String getDirector(){
		return director;
	}

	public String getTitle(){
		return title;
	}

	public String getActors(){
		return actors;
	}

	public String getImdbRating(){
		return imdbRating;
	}

	public String getImdbVotes(){
		return imdbVotes;
	}

	public String getResponse(){
		return response;
	}

	public String getRuntime(){
		return runtime;
	}

	public String getType(){
		return type;
	}

	public String getAwards(){
		return awards;
	}

	public String getYear(){
		return year;
	}

	public String getLanguage(){
		return language;
	}

	public String getRated(){
		return rated;
	}

	public String getPoster(){
		return poster;
	}

	public String getCountry(){
		return country;
	}

	public String getGenre(){
		return genre;
	}

	public String getWriter(){
		return writer;
	}

	@Override
	public String toString() {
		return "Movie{" +
				"released='" + released + "\'\n" +
				", metascore='" + metascore + "\'\n" +
				", imdbID='" + imdbID + "\'\n" +
				", plot='" + plot + "\'\n" +
				", images=" + images +
				", director='" + director + "\'\n" +
				", title='" + title + "\'\n" +
				", actors='" + actors + "\'\n" +
				", imdbRating='" + imdbRating + "\'\n" +
				", imdbVotes='" + imdbVotes + "\'\n" +
				", response='" + response + "\'\n" +
				", runtime='" + runtime + "\'\n" +
				", type='" + type + "\'\n" +
				", awards='" + awards + "\'\n" +
				", year='" + year + "\'\n" +
				", language='" + language + "\'\n" +
				", rated='" + rated + "\'\n" +
				", poster='" + poster + "\'\n" +
				", country='" + country + "\'\n" +
				", genre='" + genre + "\'\n" +
				", writer='" + writer + "\'\n" +
				'}';
	}
}