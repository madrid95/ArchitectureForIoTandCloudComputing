package io.dama.vs.hase;

public class HaSeTelegram {
	private final Integer sensorID;
	private final Integer telegramNumber;
	private final Boolean hailState;
	private final Integer plOccurences;
	private final Integer gsOccurences;
	private final Integer grOccurences;
	private final Boolean micState;
	private final Integer micCapacity;

	public static class HaSeTelegramBuilder {
		private Integer sensorID = 0;
		private Integer telegramNumber = 0;
		private Boolean hailState = false;
		private Integer plOccurences = 0;
		private Integer gsOccurences = 0;
		private Integer grOccurences = 0;
		private Boolean micState = false;
		private Integer micCapacity = 0;
		
		public static HaSeTelegramBuilder newBuilder() {
			return new HaSeTelegramBuilder();
		}
		
		public HaSeTelegram build() {
			return new HaSeTelegram(this);
		}
		/**
		 * @return the sensorID
		 */
		public Integer getSensorID() {
			return sensorID;
		}
		/**
		 * @param sensorID the sensorID to set
		 */
		public void setSensorID(Integer sensorID) {
			this.sensorID = sensorID;
		}
		/**
		 * @return the telegramNumber
		 */
		public Integer getTelegramNumber() {
			return telegramNumber;
		}
		/**
		 * @param telegramNumber the telegramNumber to set
		 */
		public void setTelegramNumber(Integer telegramNumber) {
			this.telegramNumber = telegramNumber;
		}
		/**
		 * @return the hailState
		 */
		public Boolean getHailState() {
			return hailState;
		}
		/**
		 * @param hailState the hailState to set
		 */
		public void setHailState(Boolean hailState) {
			this.hailState = hailState;
		}
		/**
		 * @return the plOccurences
		 */
		public Integer getPlOccurences() {
			return plOccurences;
		}
		/**
		 * @param plOccurences the plOccurences to set
		 */
		public void setPlOccurences(Integer plOccurences) {
			this.plOccurences = plOccurences;
		}
		/**
		 * @return the gsOccurences
		 */
		public Integer getGsOccurences() {
			return gsOccurences;
		}
		/**
		 * @param gsOccurences the gsOccurences to set
		 */
		public void setGsOccurences(Integer gsOccurences) {
			this.gsOccurences = gsOccurences;
		}
		/**
		 * @return the grOccurences
		 */
		public Integer getGrOccurences() {
			return grOccurences;
		}
		/**
		 * @param grOccurences the grOccurences to set
		 */
		public void setGrOccurences(Integer grOccurences) {
			this.grOccurences = grOccurences;
		}
		/**
		 * @return the micState
		 */
		public Boolean getMicState() {
			return micState;
		}
		/**
		 * @param micState the micState to set
		 */
		public void setMicState(Boolean micState) {
			this.micState = micState;
		}
		/**
		 * @return the micCapacity
		 */
		public Integer getMicCapacity() {
			return micCapacity;
		}
		/**
		 * @param micCapacity the micCapacity to set
		 */
		public void setMicCapacity(Integer micCapacity) {
			this.micCapacity = micCapacity;
		}
	}
	
	private HaSeTelegram(HaSeTelegramBuilder builder) {
		this.sensorID = builder.sensorID;
		this.telegramNumber = builder.telegramNumber;
		this.hailState = builder.hailState;
		this.plOccurences = builder.plOccurences;
		this.gsOccurences = builder.gsOccurences;
		this.grOccurences = builder.grOccurences;
		this.micState = builder.micState;
		this.micCapacity = builder.micCapacity;
	}

	public HaSeTelegram(String telegram) {
		this.sensorID = Integer.valueOf(telegram.substring(2, 9));
		this.telegramNumber = Integer.valueOf(telegram.substring(10, 17));
		if (telegram.charAt(18) == 'Y') {
			this.hailState = true;
		} else if (telegram.charAt(18) == 'N') {
			this.hailState = false;
		} else {
			this.hailState = false;
			System.err.println("Format-Fehler (hailState): '" + telegram + "'");
		}
		this.plOccurences = Integer.valueOf(telegram.substring(20, 26));
		this.gsOccurences = Integer.valueOf(telegram.substring(27, 33));
		this.grOccurences = Integer.valueOf(telegram.substring(34, 40));
		if (telegram.charAt(41) == 'G') {
			this.micState = true;
		} else if (telegram.charAt(41) == 'F') {
			this.micState = false;
		} else {
			this.micState = false;
			System.err.println("Format-Fehler (this.micGood): '" + telegram + "'");
		}
		this.micCapacity = Integer.valueOf(telegram.substring(43, 46));
	}

	/**
	 * @return the sensorID
	 */
	public Integer getSensorID() {
		return sensorID;
	}

	/**
	 * @return the telegramNumber
	 */
	public Integer getTelegramNumber() {
		return telegramNumber;
	}

	/**
	 * @return the hailState
	 */
	public Boolean getHailState() {
		return hailState;
	}

	/**
	 * @return the plOccurences
	 */
	public Integer getPlOccurences() {
		return plOccurences;
	}

	/**
	 * @return the gsOccurences
	 */
	public Integer getGsOccurences() {
		return gsOccurences;
	}

	/**
	 * @return the grOccurences
	 */
	public Integer getGrOccurences() {
		return grOccurences;
	}

	/**
	 * @return the micState
	 */
	public Boolean getMicState() {
		return micState;
	}

	/**
	 * @return the micCapacity
	 */
	public Integer getMicCapacity() {
		return micCapacity;
	}

	public String toString() {
		return "Sensor-ID: " + this.sensorID + ", TelegramNumber: " + this.telegramNumber + ", Hail: " + this.hailState
				+ " (PL: " + this.plOccurences + ", GS: " + this.gsOccurences + ", GR: " + this.grOccurences + "), Mic: "
				+ this.micState + " (capacity: " + this.micCapacity + ")";
	}

}