package kh.farrukh.espielspringdatajpa.relationships.phone_number;

import kh.farrukh.espielspringdatajpa.relationships.country.Country;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PhoneNumber {

    // TODO: 7/26/22 using n-to-m relationships inside embeddable class

    @Column(columnDefinition = "TEXT")
    private String countryCode;
    @Column(columnDefinition = "TEXT")
    private String localNumber;

    @ManyToOne
    @JoinColumn(name = "pn_country_id")
    private Country country;
}
