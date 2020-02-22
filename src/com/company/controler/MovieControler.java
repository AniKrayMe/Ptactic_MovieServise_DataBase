package com.company.controler;

import com.company.models.Movie;
import com.company.services.MovieService;

import java.util.Scanner;

public class MovieControler {
    private MovieService movieService = MovieService.getInstance();





    void  initializeAndCreateMovie(){
        Scanner scanner =  new Scanner( System.in );
        System.out.println( "Please print Name and Genre" );
        String name = scanner.nextLine();
        String genre = scanner.nextLine();
        Movie movie = new Movie( name, genre );
        movieService.createMovie(movie);
    }


    void printAllMovies(){
        movieService.showMovies();
    }

    void updateMovie(){
        Scanner scanner = new Scanner( System.in );
        String newName = scanner.nextLine();
        String newGenre = scanner.nextLine();
        int id = scanner.nextInt();
        Movie movie = new Movie( newName, newGenre);
        movieService.updateMovieByID( movie,id );

    }

    void deleteMovie(){
        Scanner scanner = new Scanner( System.in );
        int id = scanner.nextInt();
        movieService.deleteMovieByID( id );
    }

}


