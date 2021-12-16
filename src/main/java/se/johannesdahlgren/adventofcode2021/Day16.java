package se.johannesdahlgren.adventofcode2021;

import java.math.BigInteger;

public class Day16 {

  private final String bitsTransmissionBinary;
  private int currentIndex;

  public Day16(String BITSTransmission) {
    bitsTransmissionBinary = hexToBin(BITSTransmission);
  }


  public int getVersionNumberSum() {
    System.out.println(bitsTransmissionBinary);
    currentIndex = 0;
    return getPacketVersion();
  }

  private int getPacketVersion() {
    int subPackageVersionSum = 0;
    String packetVersionString = bitsTransmissionBinary.substring(currentIndex, currentIndex += 3);
    int packetVersion = Integer.parseInt(packetVersionString, 2);
    System.out.printf("PacketVersionString: %s, packetVersion: %s \n", packetVersionString, packetVersion);
    String packetTypeIdString = bitsTransmissionBinary.substring(currentIndex, currentIndex += 3);
    int packetTypeId = Integer.parseInt(packetTypeIdString, 2);
    System.out.printf("PacketTypeString: %s, packetType: %s \n", packetTypeIdString, packetTypeId);

    if (packetTypeId == 4) {
      String literalValue = getLiteralValue();
      System.out.printf("LiteralValueString: %s, literalValue: %s \n", literalValue, new BigInteger(literalValue, 2));
    } else {
      String lengthTypeIdString = bitsTransmissionBinary.substring(currentIndex, currentIndex += 1);
      int lengthTypeId = Integer.parseInt(lengthTypeIdString, 2);
      System.out.printf("lengthTypeIdString: %s, lengthTypeId: %s\n", lengthTypeIdString, lengthTypeId);
      if (lengthTypeId == 0) {
        String subPacketLengthString = bitsTransmissionBinary.substring(currentIndex, currentIndex += 15);
        int subPacketLength = Integer.parseInt(subPacketLengthString, 2);
        int currentIndexNow = currentIndex;
        do {
          subPackageVersionSum += getPacketVersion();
        } while (currentIndex < currentIndexNow + subPacketLength);
      } else {
        String numberOfSubPacketsString = bitsTransmissionBinary.substring(currentIndex, currentIndex += 11);
        int numberOfSubPackets = Integer.parseInt(numberOfSubPacketsString, 2);
        for (int i = 0; i < numberOfSubPackets; i++) {
          subPackageVersionSum += getPacketVersion();
        }
      }
    }
    return packetVersion + subPackageVersionSum;
  }

  private String getLiteralValue() {
    StringBuilder binaryValue = new StringBuilder();
    String literalValueGroup;
    do {
      literalValueGroup = bitsTransmissionBinary.substring(currentIndex, currentIndex += 5);

      String substring = literalValueGroup.substring(1);
      System.out.printf("LiteralValueGroup: %s, literalValueString: %s\n", literalValueGroup, substring);
      binaryValue.append(substring);

    } while (literalValueGroup.startsWith("1"));
    return binaryValue.toString();
  }

  private String hexToBin(String hex){
    hex = hex.replaceAll("0", "0000");
    hex = hex.replaceAll("1", "0001");
    hex = hex.replaceAll("2", "0010");
    hex = hex.replaceAll("3", "0011");
    hex = hex.replaceAll("4", "0100");
    hex = hex.replaceAll("5", "0101");
    hex = hex.replaceAll("6", "0110");
    hex = hex.replaceAll("7", "0111");
    hex = hex.replaceAll("8", "1000");
    hex = hex.replaceAll("9", "1001");
    hex = hex.replaceAll("A", "1010");
    hex = hex.replaceAll("B", "1011");
    hex = hex.replaceAll("C", "1100");
    hex = hex.replaceAll("D", "1101");
    hex = hex.replaceAll("E", "1110");
    hex = hex.replaceAll("F", "1111");
    return hex;
  }
}
