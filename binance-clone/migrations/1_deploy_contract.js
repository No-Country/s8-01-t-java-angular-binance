// const hre = require('hardhat')

// async function main() {
//   const Gallery = await hre.ethers.getContractFactory('Gallery')
//   const gallery = await Gallery.deploy()
//   await gallery.deployed()

//   const txHash = gallery.deployTransaction.hash;
//   const txReceipt = await hre.ethers.provider.waitForTransaction(txHash);
//   console.log(`check your contract: https://sepolia.etherscan.io/address/${txReceipt.contractAddress}`)
//   // console.log(`check your contract: https://mumbai.polygonscan.com/address/${txReceipt.contractAddress}`)
//   console.log("contract address:", txReceipt.contractAddress);

//   // 0e6029260f7f3216a5698998cf86a6fe2c48dd141c770aa9107a246408b1eb5d
// }

// main()
//   .then(() => process.exit(0))
//   .catch((error) => {
//     console.error(error)
//     process.exit(1)
//   })

const Gallery = artifacts.require("Gallery");

module.exports = async function (deployer) {
  await deployer.deploy(Gallery);
  const instance = await Gallery.deployed();
  console.log("Contract deployed at address:", instance.address);
};
