package com.scaler.lld.bookmyshow;

import com.scaler.lld.bookmyshow.controller.TicketController;
import com.scaler.lld.bookmyshow.dto.ticket.BookTicketRequestDto;
import com.scaler.lld.bookmyshow.dto.ticket.BookTicketResponseDto;
import com.scaler.lld.bookmyshow.model.movie.Actor;
import com.scaler.lld.bookmyshow.model.movie.Genre;
import com.scaler.lld.bookmyshow.model.movie.Movie;
import com.scaler.lld.bookmyshow.model.show.*;
import com.scaler.lld.bookmyshow.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication(scanBasePackages = "com.scaler.lld.bookmyshow")
public class BookMyShowApplication implements CommandLineRunner {

	private final TicketController ticketController;

	private final MovieRepository movieRepository;
	private final CityRepository cityRepository;

	private final TheaterRepository theaterRepository;
	private final AuditoriumRepository auditoriumRepository;
	private final SeatTypeRepository seatTypeRepository;
	private final ShowRepository showRepository;

	private final SeatRepository seatRepository;

	private final ShowSeatRepository showSeatRepository;
	private final ShowSeatTypeRepository showSeatTypeRepository;

	@Autowired
	public BookMyShowApplication(TicketController ticketController,
								 MovieRepository movieRepository,
								 CityRepository cityRepository,
								 TheaterRepository theaterRepository,
								 AuditoriumRepository auditoriumRepository,
								 SeatTypeRepository seatTypeRepository, ShowRepository showRepository, SeatRepository seatRepository, ShowSeatRepository showSeatRepository, ShowSeatTypeRepository showSeatTypeRepository) {
		this.ticketController = ticketController;
		this.movieRepository = movieRepository;
		this.cityRepository = cityRepository;
		this.theaterRepository = theaterRepository;
		this.auditoriumRepository = auditoriumRepository;
		this.seatTypeRepository = seatTypeRepository;
		this.showRepository = showRepository;
		this.seatRepository = seatRepository;
		this.showSeatRepository = showSeatRepository;
		this.showSeatTypeRepository = showSeatTypeRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		create a movie
		Movie movie = new Movie();
		movie.setTitle("spiderman: no way home");
		movie.setGenre(Genre.ACTION);
		movie.setDurationInMinute(124);
		movie.setActors(Set.of(new Actor("peter parker"), new Actor("happy hogan")));
		Movie savedMovie = movieRepository.save(movie);

//		create city, theater
		City bhubaneswar = new City("bhubaneswar");
		City savedCity = cityRepository.save(bhubaneswar);
		Long savedCityId = savedCity.getId();

		Theater theater = new Theater();
		theater.setName("inox regalia");
		theater.setCity(cityRepository.getReferenceById(savedCityId));
		Theater savedTheater = theaterRepository.save(theater);
		Long savedTheaterId = savedTheater.getId();

//		add auditorium to a theater
		Auditorium auditorium = new Auditorium();
		auditorium.setName("screen 1");
		auditorium.setTheater(theaterRepository.getReferenceById(savedTheaterId));
		auditorium.setFeatures(Set.of(Feature.THREE_D, Feature.DOLBY));
		Auditorium savedAuditorium = auditoriumRepository.save(auditorium);

//		create 3 seat types: vip, gold and platinum
		SeatType vip = new SeatType("vip");
		SeatType gold = new SeatType("gold");
		SeatType platinum = new SeatType("platinum");
		seatTypeRepository.saveAll(Set.of(vip, gold, platinum));

//		create 30 seats in the auditorium
		List<Seat> vipSeats = IntStream.range(1, 11).mapToObj(i ->
				new Seat("A" + i, 1, i, seatTypeRepository.findFirstByName("vip"),
						auditoriumRepository.getReferenceById(savedAuditorium.getId()))).collect(Collectors.toList());
		List<Seat> goldSeats = IntStream.range(1, 11).mapToObj(i ->
				new Seat("B" + i, 2, i, seatTypeRepository.findFirstByName("gold"),
						auditoriumRepository.getReferenceById(savedAuditorium.getId()))).collect(Collectors.toList());
		List<Seat> platinumSeats = IntStream.range(1, 11).mapToObj(i ->
				new Seat("C" + i, 3, i, seatTypeRepository.findFirstByName("platinum"),
						auditoriumRepository.getReferenceById(savedAuditorium.getId()))).collect(Collectors.toList());

		List<Seat> seats = Stream.of(vipSeats, goldSeats, platinumSeats).flatMap(Collection::stream).toList();
		seatRepository.saveAll(seats);

// 		create a show
		Show eveningShow = new Show();
		eveningShow.setName("evening show");
		eveningShow.setAuditorium(auditoriumRepository.getReferenceById(savedAuditorium.getId()));
		eveningShow.setFeatures(List.of(Feature.THREE_D, Feature.FOUR_D_MAX));
		eveningShow.setMovie(movieRepository.getReferenceById(savedMovie.getId()));
		eveningShow.setStartTime(LocalTime.of(18, 0));
		eveningShow.setEndTime(LocalTime.of(21, 00));
		Show savedShow = showRepository.save(eveningShow);

//		add show seats
		List<Seat> screen1Seats = seatRepository.findByAuditoriumId(savedAuditorium.getId());
		List<ShowSeat> showSeats = screen1Seats.stream().map(seat ->
				new ShowSeat(showRepository.getReferenceById(savedShow.getId()), seat, BookingStatus.AVAILABLE)).toList();
		showSeatRepository.saveAll(showSeats);

//		add show price for each seat type
		ShowSeatType eveningShowVip = new ShowSeatType(showRepository.getReferenceById(savedShow.getId()), seatTypeRepository.findFirstByName("vip"), 200);
		ShowSeatType eveningShowGold = new ShowSeatType(showRepository.getReferenceById(savedShow.getId()), seatTypeRepository.findFirstByName("gold"), 300);
		ShowSeatType eveningShowPlatinum = new ShowSeatType(showRepository.getReferenceById(savedShow.getId()), seatTypeRepository.findFirstByName("platinum"), 500);
		List<ShowSeatType> showSeatTypes = List.of(eveningShowVip, eveningShowGold, eveningShowPlatinum);
		showSeatTypeRepository.saveAll(showSeatTypes);


//		todo - try booking two requests in parallel with overlapping seats
		BookTicketRequestDto ticketFor3 = new BookTicketRequestDto(
				savedMovie.getId(), savedShow.getId(), List.of(1L, 2L, 3L));
		BookTicketRequestDto ticketFor2 = new BookTicketRequestDto(
				savedMovie.getId(), savedShow.getId(), List.of(4L, 3L));

		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Callable<BookTicketResponseDto> bookTicketFor3 = () -> {
			BookTicketResponseDto book = ticketController.book(ticketFor3);
			System.out.println(book);
			return book;
		};

		Callable<BookTicketResponseDto> bookTicketFor2 = () -> {
			BookTicketResponseDto book = ticketController.book(ticketFor2);
			System.out.println(book);
			return book;
		};
		executorService.submit(bookTicketFor3);
		executorService.submit(bookTicketFor2);
	}
}
