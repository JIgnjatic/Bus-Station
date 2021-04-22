package com.JovicaIgnjatic;

import java.io.Serializable;
import java.time.LocalTime;

//Autobuska stanica
//		Napisati Windows aplikaciju koja sluzi za rad na autobuskoj stanici. Treba imati sledece
//		podatke:
//		Mesta – spisak mesta kroz koje prolazi bar jedna autobuska linija. Taj spisak se moze
//		dopunjavati novim mestima, a takodje se pojedina mestamogu brisati (to sve treba da
//		obezbedi aplikacija – program)
//		Odlasci – spisak odlazaka iz te stanice. Svaki odlazak ima sledece informacije: vreme
//		polaska, krajnja stanica, vreme dolaska u krajnju stanicu, ucestalost polazaka (svaki dan,
//		samo radnim danom, samo vikendom), usputne stanice (nazivi i vreme dolaska)
//		Dolasci – spisak dolazaka u tu stanicu. Svaki dolazak ima sledece informacije: vreme
//		dolaska, polazna stanica, vreme polaska iz polazne stanice, ucestalost dolazaka (svaki
//		dan, samo radnim danom, samo vikendom), usputne stanice (nazivi i vreme polaska).
//		Rezervacije – svaka rezervacija sadrzi ime i prezime osote koja rezervise, koliko barata
//		rezervise, datum za koji se rezervise, linija za koju se rezervise.
//		Svaki od ovih spiskova s emoze azurirati dodavanjem novi polazaka, dolazaka ili
//		rezervacija.
//		Obezbediti sledece dodatne usluge:
//		- listanje svih linija koje polaze iz nase stanice a prolaze kroz izabrano mesto (ili
//		dolaze u to izabrano mesto). Spisak sortirati po vremenu polaska
//		- listanje svih linija koje dolaze u nasu stanicu a prolaze kroz izabrano mesto (ili
//		polaze iz tog izabranog mesta). Spisak sortirati po vremenu dolaska
//		- Spisak svi rezervacija za izabrani polazak.
//		Kada se izadje iz aplikaciji, unete podatke treba sacuvati tako da se vide pri ponovnom
//		startovanju programa.
public class Station implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3985157571659838445L;
	private String name;
	private int id;
	private LocalTime arrival;
	private LocalTime departure;
	public Station(String name, int id) {
		this.name = name;
		this.id=id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setArrival(int hour, int minute) {
		this.arrival = LocalTime.of(hour,minute);
	}

	public void setDeparture(int hour, int minute) {
		this.departure = LocalTime.of(hour,minute);
	}

	public LocalTime getArrival() {
		return arrival;
	}

	public LocalTime getDeparture() {
		return departure;
	}

	@Override
	public String toString() {
		return
				"name='" + name + '\'' +
				", id=" + id +
				", arrival=" + arrival +
				", departure=" + departure ;
	}
}
