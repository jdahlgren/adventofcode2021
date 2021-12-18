package se.johannesdahlgren.adventofcode2021;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class Day16Test {

  @Test
  void part1Example1() {
    Day16 day16 = new Day16("D2FE28");
    int versionNumberSum = day16.getVersionNumberSum();
    assertThat(versionNumberSum, Is.is(6));
  }

  @Test
  void part1Example2() {
    Day16 day16 = new Day16("38006F45291200");
    int versionNumberSum = day16.getVersionNumberSum();
    assertThat(versionNumberSum, Is.is(9));
  }

  @Test
  void part1Example3() {
    Day16 day16 = new Day16("EE00D40C823060");
    int versionNumberSum = day16.getVersionNumberSum();
    assertThat(versionNumberSum, Is.is(14));
  }

  @Test
  void part1Example4() {
    Day16 day16 = new Day16("8A004A801A8002F478");
    int versionNumberSum = day16.getVersionNumberSum();
    assertThat(versionNumberSum, Is.is(16));
  }

  @Test
  void part1Example5() {
    Day16 day16 = new Day16("620080001611562C8802118E34");
    int versionNumberSum = day16.getVersionNumberSum();
    assertThat(versionNumberSum, Is.is(12));
  }

  @Test
  void part1Example6() {
    Day16 day16 = new Day16("C0015000016115A2E0802F182340");
    int versionNumberSum = day16.getVersionNumberSum();
    assertThat(versionNumberSum, Is.is(23));
  }

  @Test
  void part1Example7() {
    Day16 day16 = new Day16("A0016C880162017C3686B18A3D4780");
    int versionNumberSum = day16.getVersionNumberSum();
    assertThat(versionNumberSum, Is.is(31));
  }

  @Test
  void part1() {
    Day16 day16 = new Day16("420D5A802122FD25C8CD7CC010B00564D0E4B76C7D5A59C8C014E007325F116C958F2C7D31EB4EDF90A9803B2EB5340924CA002761803317E2B4793006E28C2286440087C5682312D0024B9EF464DF37EFA0CD031802FA00B4B7ED2D6BD2109485E3F3791FDEB3AF0D8802A899E49370012A926A9F8193801531C84F5F573004F803571006A2C46B8280008645C8B91924AD3753002E512400CC170038400A002BCD80A445002440082021DD807C0201C510066670035C00940125D803E170030400B7003C0018660034E6F1801201042575880A5004D9372A520E735C876FD2C3008274D24CDE614A68626D94804D4929693F003531006A1A47C85000084C4586B10D802F5977E88D2DD2898D6F17A614CC0109E9CE97D02D006EC00086C648591740010C8AF14E0E180253673400AA48D15E468A2000ADCCED1A174218D6C017DCFAA4EB2C8C5FA7F21D3F9152012F6C01797FF3B4AE38C32FFE7695C719A6AB5E25080250EE7BB7FEF72E13980553CE932EB26C72A2D26372D69759CC014F005E7E9F4E9FA7D3653FCC879803E200CC678470EC0010E82B11E34080330D211C663004F00101911791179296E7F869F9C017998EF11A1BCA52989F5EA778866008D8023255DFBB7BD2A552B65A98ECFEC51D540209DFF2FF2B9C1B9FE5D6A469F81590079160094CD73D85FD2699C5C9DCF21F0700094A1AC9EDA64AE3D37D34200B7B401596D678A73AFB2D0B1B88057230A42B2BD88E7F9F0C94F1ECB7B0DD393489182F9802D3F875C00DC40010F8911C61F8002111BA1FC2E400BEA5AA0334F9359EA741892D81100B83337BD2DDB4E43B401A800021F19A09C1F1006229C3F8726009E002A12D71B96B8E49BB180273AA722468002CC7B818C01B04F77B39EFDF53973D95ADB5CD921802980199CF4ADAA7B67B3D9ACFBEC4F82D19A4F75DE78002007CD6D1A24455200A0E5C47801559BF58665D80");
    int versionNumberSum = day16.getVersionNumberSum();
    assertThat(versionNumberSum, Is.is(993));
  }
}