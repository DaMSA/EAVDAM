package dk.frv.eavdam.utils;

import dk.frv.eavdam.data.FATDMACell;	
import dk.frv.eavdam.data.FATDMADefaultChannel;	
import dk.frv.eavdam.data.FATDMANode;
import dk.frv.eavdam.io.DefaultFATDMAReader;
import dk.frv.eavdam.layers.FATDMAGridLayer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
	
public class FATDMAUtils {

	public static Map<String,List<FATDMACell>> fatdmaCellsMap = null;
	
	public static boolean areReservedBlocksAccordingToFATDMAScheme(float lat, float lon, List<Integer> reservedBlocksForChannelA, List<Integer> reservedBlocksForChannelB) {

		int singleCellSizeInNauticalMiles = 30;
		int noOfSingleCellsAlongOneSideOfMasterCell = 6;
		int masterCellSizeInNauticalMiles = singleCellSizeInNauticalMiles * noOfSingleCellsAlongOneSideOfMasterCell;		
		int noOfMasterCellsAroundEquator = (int) (360.0d * 60.0d / masterCellSizeInNauticalMiles);
		float masterCellSizeInDegreesLatitude = (float) masterCellSizeInNauticalMiles / 60;  	
		float singleCellHeightInDegrees = masterCellSizeInDegreesLatitude / noOfSingleCellsAlongOneSideOfMasterCell;

		int masterCellRowNo = (int) (Math.abs(lat + (float) 0.5*singleCellHeightInDegrees) / masterCellSizeInDegreesLatitude);
		double masterCellMeanLatitude = (masterCellRowNo + 0.5) * masterCellSizeInDegreesLatitude;
		int noOfMasterCellsAroundMasterCellRow = (int) (noOfMasterCellsAroundEquator * Math.cos(2*Math.PI*masterCellMeanLatitude/360.0));
		float singleCellWidthInDegrees = (float) 360/(noOfSingleCellsAlongOneSideOfMasterCell*noOfMasterCellsAroundMasterCellRow);	
									
		int cellNumber = FATDMAGridLayer.getCellNo(lat, lon, singleCellSizeInNauticalMiles, noOfSingleCellsAlongOneSideOfMasterCell, masterCellRowNo, singleCellWidthInDegrees);
		
		if (fatdmaCellsMap == null) {
			fatdmaCellsMap = DefaultFATDMAReader.readDefaultValues(null, null);
		}
		
		List<Integer> acceptedFATDMABlocksForChannelA = new ArrayList<Integer>();
		List<Integer> acceptedFATDMABlocksForChannelB = new ArrayList<Integer>();
		
		List<FATDMACell> fatdmaICells = fatdmaCellsMap.get(String.valueOf(cellNumber) + "-I");
		acceptedFATDMABlocksForChannelA = processFATDMACellsChannelA(fatdmaICells, acceptedFATDMABlocksForChannelA);
		acceptedFATDMABlocksForChannelB = processFATDMACellsChannelA(fatdmaICells, acceptedFATDMABlocksForChannelB);
		List<FATDMACell> fatdmaIICells = fatdmaCellsMap.get(String.valueOf(cellNumber) + "-II");
		acceptedFATDMABlocksForChannelA = processFATDMACellsChannelA(fatdmaIICells, acceptedFATDMABlocksForChannelA);
		acceptedFATDMABlocksForChannelB = processFATDMACellsChannelA(fatdmaIICells, acceptedFATDMABlocksForChannelB);
		
		if (reservedBlocksForChannelA != null) {
			for (Integer block : reservedBlocksForChannelA) {
				if (!acceptedFATDMABlocksForChannelA.contains(block)) {
					return false;
				}
			}
		}

		if (reservedBlocksForChannelB != null) {
			for (Integer block : reservedBlocksForChannelB) {
				if (!acceptedFATDMABlocksForChannelB.contains(block)) {
					return false;
				}
			}
		}

		return true;		
	}
	
	private static List<Integer> processFATDMACellsChannelA(List<FATDMACell> fatdmaCells, List<Integer> acceptedFATDMABlocks) {
	
		for (FATDMACell fatdmaCell : fatdmaCells) {
			
			FATDMADefaultChannel channelA = fatdmaCell.getChannelA();
			
			FATDMANode channelASemaphoreNode = channelA.getSemaphoreNode();
			List<Integer> channelASemaphoreNodeAcceptedFATDMABlocks =
				getBlocks(channelASemaphoreNode.getStartingSlot(), channelASemaphoreNode.getBlockSize(), channelASemaphoreNode.getIncrement());
			for (Integer block : channelASemaphoreNodeAcceptedFATDMABlocks) {
				if (!acceptedFATDMABlocks.contains(block)) {
					acceptedFATDMABlocks.add(block);
				}
			}
			
			FATDMANode channelANonSemaphoreNode = channelA.getNonSemaphoreNode();
			List<Integer> channelANonSemaphoreNodeAcceptedFATDMABlocks =
				getBlocks(channelANonSemaphoreNode.getStartingSlot(), channelANonSemaphoreNode.getBlockSize(), channelANonSemaphoreNode.getIncrement());
			for (Integer block : channelANonSemaphoreNodeAcceptedFATDMABlocks) {
				if (!acceptedFATDMABlocks.contains(block)) {
					acceptedFATDMABlocks.add(block);
				}
			}
			
		}
		
		return acceptedFATDMABlocks;
	
	}
			
	private static List<Integer> processFATDMACellsChannelB(List<FATDMACell> fatdmaCells, List<Integer> acceptedFATDMABlocks) {
	
		for (FATDMACell fatdmaCell : fatdmaCells) {	
	
			FATDMADefaultChannel channelB = fatdmaCell.getChannelB();		

			FATDMANode channelBSemaphoreNode = channelB.getSemaphoreNode();
			List<Integer> channelBSemaphoreNodeAcceptedFATDMABlocks =
				getBlocks(channelBSemaphoreNode.getStartingSlot(), channelBSemaphoreNode.getBlockSize(), channelBSemaphoreNode.getIncrement());
			for (Integer block : channelBSemaphoreNodeAcceptedFATDMABlocks) {
				if (!acceptedFATDMABlocks.contains(block)) {
					acceptedFATDMABlocks.add(block);
				}
			}
			
			FATDMANode channelBNonSemaphoreNode = channelB.getNonSemaphoreNode();
			List<Integer> channelBNonSemaphoreNodeAcceptedFATDMABlocks =
				getBlocks(channelBNonSemaphoreNode.getStartingSlot(), channelBNonSemaphoreNode.getBlockSize(), channelBNonSemaphoreNode.getIncrement());
			for (Integer block : channelBNonSemaphoreNodeAcceptedFATDMABlocks) {
				if (!acceptedFATDMABlocks.contains(block)) {
					acceptedFATDMABlocks.add(block);
				}
			}
			
		}
		
		return acceptedFATDMABlocks;
	}
	
	public static List<Integer> getBlocks(Integer startslot, Integer blockSize, Integer increment) {

		List<Integer> blocks = new ArrayList<Integer>();
	
		if (startslot != null && blockSize != null && increment != null) {
			int startslotInt = startslot.intValue();
			int blockSizeInt = blockSize.intValue();
			int incrementInt = increment.intValue();
			if (incrementInt == 0) {
				for (int i=0; i<blockSizeInt; i++) {
					Integer slot = new Integer(startslotInt+i);
					if (!blocks.contains(slot)) {
						blocks.add(slot);
					};
				}								
			} else if (incrementInt > 0) {
				int i = 0;
				while (i*incrementInt <= 2249) {							
					for (int j=0; j<blockSizeInt; j++) {
						Integer slot = new Integer(startslotInt+j+(i*incrementInt));
						if (!blocks.contains(slot)) {										
							blocks.add(slot);
						}
					}
					i++;
				}
			}
		}
		
		return blocks;	
	}
			
}