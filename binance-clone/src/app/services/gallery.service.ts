import { Injectable, OnInit } from '@angular/core';
import { ethers } from 'ethers';
import { environment } from '../../environments/environment';
import Gallery from 'abis/Gallery.json';
import detectEthereumProvider from '@metamask/detect-provider';
import Web3 from 'web3';
import tokenABI from './tokenABI';

declare global {
  interface Window {
    ethereum: any;
  }
}

@Injectable({
  providedIn: 'root',
})
export class GalleryService implements OnInit {
  private images: any[] = [];
  private web3!: Web3;
  private currentNetwork!: string;
  private provider = new ethers.providers.Web3Provider(window.ethereum);
  tokenAddresses = [
    {
      address: '0x2b591e99afe9f32eaa6214f7b7629768c40eeb39',
      token: 'HEX',
    },
    {
      address: '0x3d658390460295fb963f54dc0899cfb1c30776df',
      token: 'COVAL',
    },
    {
      address: '0x6b175474e89094c44da98b954eedeac495271d0f',
      token: 'DAI',
    },
  ];

  constructor() {
    // // Crea una instancia de web3
    // this.web3 = new Web3(Web3.givenProvider);

    // // this.getMyBalance();
    // this.currentNetwork = '';

    // if (typeof window.ethereum !== 'undefined') {
    //   this.web3 = new Web3(window.ethereum);
    //   console.log('MetaMask is installed!');
    //   this.getNetworkId();
    //   alert('MetaMask is installed!');
    // } else {
    //   console.error('MetaMask not found!');
    //   alert('MetaMask is not installed.');
    // }

    // if (typeof window.ethereum !== 'undefined') {
    //   // MetaMask is installed and has an Ethereum provider
    //   this.web3 = new Web3(window.ethereum);
    //   console.log('MetaMask is installed!');
    //   this.getNetworkId();
    //   alert('MetaMask is installed!');
    // } else if (typeof Web3.givenProvider !== 'undefined') {
    //   // MetaMask not detected, but there is an Ethereum provider available
    //   this.web3 = new Web3(Web3.givenProvider);
    //   console.log('No MetaMask, but Ethereum provider is available');
    //   alert('No MetaMask, but Ethereum provider is available');
    // } else {
    //   // Neither MetaMask nor any Ethereum provider found
    //   console.error('No MetaMask or Ethereum provider found!');
    //   alert('MetaMask is not installed.');
    // }

    if (typeof window.ethereum !== 'undefined') {
      // MetaMask está instalado y tiene un proveedor de Ethereum
      this.web3 = new Web3(window.ethereum);
      // console.log('MetaMask está instalado!');
      this.getNetworkId();
      // alert('¡MetaMask está instalado!');

      // Escuchar los cambios de red
      window.ethereum.on('chainChanged', (chainId: string) => {
        // Manejar el evento de cambio de red
        console.log('Red cambiada:', chainId);
        alert('Red cambiada:');
        this.getNetworkId();
      });
    } else {
      // MetaMask no encontrado
      console.error('¡MetaMask no encontrado!');
      alert('MetaMask no está instalado.');
    }
  }

  async ngOnInit() {}

  // Metamask
  getAccountAddress(): string | null {
    if (this.web3.givenProvider && this.web3.givenProvider.selectedAddress) {
      return this.web3.givenProvider.selectedAddress;
    } else {
      console.error('MetaMask no está instalado o no hay una cuenta logueada');
      return null;
    }
  }

  async getNetworkId() {
    try {
      const networkId = await this.web3.eth.net.getId();
      this.currentNetwork = networkId.toString();

      // Verificar si la red actual es diferente a la red esperada (por ejemplo, red de prueba de Ropsten)
      if (networkId !== parseInt('11155111')) {
        // Mostrar mensaje al usuario indicando que debe cambiar a la red de Ethereum esperada
        alert('La red actual no es Ethereum. Se requiere la red Ethereum.');
        console.log(
          'La red actual no es Ethereum. Se requiere la red Ethereum.'
        );
      }
      console.log('ID de red:', networkId);
    } catch (error) {
      console.error('Error al obtener el Network ID:', error);
      // Manejar el error de obtener el Network ID
    }
  }

  getMyBalance(): void {
    const address = this.getAccountAddress();
    if (address) {
      this.web3.eth.getBalance(address, (error: any, balance: any) => {
        if (error) {
          console.error('Error al obtener el saldo:', error);
        } else {
          const balanceInEther = ethers.utils.formatEther(balance);
          console.log('Balance:', balanceInEther, 'ETH');
        }
      });
    } else {
      console.error('MetaMask no está instalado o no hay una cuenta logueada');
    }
  }

  // Obtener el saldo de un token para una dirección específica
  private async getTokenBalance(
    tokenAddress: string,
    address: string
  ): Promise<string> {
    const tokenInst = new ethers.Contract(
      tokenAddress,
      tokenABI,
      this.provider
    );
    const balance = await tokenInst['balanceOf'](address);
    return balance.toString();
  }

  // Obtener los saldos de los tokens para la dirección de la cuenta actual
  public async getBalancesForTokens(): Promise<void> {
    const address = this.getAccountAddress();
    if (address) {
      for (const token of this.tokenAddresses) {
        const balance = await this.getTokenBalance(token.address, address);
        console.log(`Balance of ${token.token}: ${balance}`);
      }
    }
  }

  // Gallery
  public async getAllImages(): Promise<any[]> {
    const contract = await GalleryService.getContract();
    return await contract['retrieveAllImages']();
  }

  public async getImagesByAuthor(): Promise<any[]> {
    const contract = await GalleryService.getContract(true);

    return await contract['retrieveImagesByAuthor']();
  }

  public async addImage(title: string, fileUrl: string): Promise<boolean> {
    const contract = await GalleryService.getContract(true);
    const transaction = await contract['store'](title, fileUrl);
    const tx = await transaction.wait();

    return tx.status === 1;
  }

  private static async getContract(bySigner = false) {
    const provider = await GalleryService.getWebProvider();
    const signer = provider.getSigner();

    return new ethers.Contract(
      environment.contractAddress,
      Gallery.abi,
      bySigner ? signer : provider
    );
  }

  private static async getWebProvider(requestAccounts = true) {
    const provider: any = await detectEthereumProvider();

    if (requestAccounts) {
      await provider.request({ method: 'eth_requestAccounts' });
    }

    return new ethers.providers.Web3Provider(provider);
  }

  setImages(images: any[]): void {
    this.images = images;
  }

  getImages(): any[] {
    return this.images;
  }
}
