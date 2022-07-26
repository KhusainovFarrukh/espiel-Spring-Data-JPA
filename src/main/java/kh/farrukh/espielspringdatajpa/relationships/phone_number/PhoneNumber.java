package kh.farrukh.espielspringdatajpa.relationships.phone_number;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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
}
