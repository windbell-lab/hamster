package org.windbell.lab.hamster.junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServletTest extends AbstractJUnit {
	@Autowired
	private IOriginAndDestinationService service;
	private OriginADestinationRequestDTO request;

	@Before
	public void init() {
		this.request = new OriginADestinationRequestDTO();
		this.request.setBrand("KA");
		this.request.setBookingFlow("REV");
		this.request.setLocale("en_HK");
		this.request.setOrigin("HKG");
		this.request.setSector(0);
		this.request.setCorrelationInfo(new CorrelationInfo("aaaa", "bbbb", "cccc"));
	}

	@Test
	public void testService() {
		OriginADestinationResponseDTO response = this.service.invoke(this.request);
		Assert.assertNotNull(response);
	}}