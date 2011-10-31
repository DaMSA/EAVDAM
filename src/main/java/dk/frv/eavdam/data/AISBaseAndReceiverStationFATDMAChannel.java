package dk.frv.eavdam.data;import java.util.List;public class AISBaseAndReceiverStationFATDMAChannel extends FATDMAChannel {	private List<FATDMAReservation> fatdmaScheme;	public AISBaseAndReceiverStationFATDMAChannel() {		super();	}		public AISBaseAndReceiverStationFATDMAChannel(String channelName) {		super(channelName);	}	public AISBaseAndReceiverStationFATDMAChannel(String channelName, List<FATDMAReservation> fatdmaScheme) {		super(channelName);		this.fatdmaScheme = fatdmaScheme;	}		public List<FATDMAReservation> getFATDMAScheme() {		return fatdmaScheme;	}		public void setFatdmaScheme(List<FATDMAReservation> fatdmaScheme) {		this.fatdmaScheme = fatdmaScheme;	}}