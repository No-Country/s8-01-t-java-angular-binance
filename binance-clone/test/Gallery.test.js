const Galley = artifacts.require("./Gallery.sol");

require("chai").use(require("chai-as-promised")).should();

contract("Galley", () => {
  let galley;

  before(async () => {
    galley = await Galley.deployed();
  });

  describe("deployment", async () => {
    it("deploys successfully", async () => {
      const address = await galley.address;
      assert.notEqual(address, 0x0);
      assert.notEqual(address, "");
      assert.notEqual(address, null);
      assert.notEqual(address, undefined);
    });

    it("has a name", async () => {
      const name = await galley.name();
      assert.equal(name, "Dapp No-Country Gallery");
    });
  });
});
