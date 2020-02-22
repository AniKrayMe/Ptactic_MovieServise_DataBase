package com.company.services;

import com.company.constant.MariaDBConstant;
import com.company.models.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private static MovieService objectRefrense = null;


    private MovieService() {
    }

    public static MovieService getInstance() {
        if (objectRefrense == null ) {
            objectRefrense = new MovieService();
            return objectRefrense;
        } else {
            return objectRefrense;
        }
    }

    public void createMovie(Movie movie) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection( MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass);
            if (conn != null) {
                String query = "INSERT iNTO movie (name, genre) VALUES (?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement( query );
                preparedStatement.setString( 1, movie.getName() );
                preparedStatement.setString( 2, movie.getGenre() );
                preparedStatement.executeUpdate();
                System.out.println( "added" );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Movie> showMovies(){

        Connection conn = null;
        List<Movie> movies = new ArrayList<>(  );
        try {
            conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass);
            if (conn != null) {
                String query = "SELECT * FROM movie";
                PreparedStatement preparedStatement = conn.prepareStatement( query );
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    String name = resultSet.getString( "name" );
                    String genre = resultSet.getString( "genre" );
                    movies.add(new Movie( name,genre ));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return  movies;
    }


    public void updateMovieByID(Movie movie, int id){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass);
            if (conn != null) {
                String query = "UPDATE  movie SET name = ?, genre = ? WHERE id = ?" ;
                PreparedStatement preparedStatement = conn.prepareStatement( query );
                preparedStatement.setString( 1,movie.getName() );
                preparedStatement.setString( 2,movie.getGenre() );
                preparedStatement.setInt( 3, id);
                preparedStatement.executeUpdate();
                System.out.println( "updated" );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public void deleteMovieByID( int id){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass);
            if (conn != null) {
                String query = "DELETE FROM movie SET WHERE id = ?" ;
                PreparedStatement preparedStatement = conn.prepareStatement( query );
                preparedStatement.setInt( 1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}


