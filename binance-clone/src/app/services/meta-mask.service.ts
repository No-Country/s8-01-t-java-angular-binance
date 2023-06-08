// import { Injectable } from '@angular/core';
// import Web3 from 'web3';

// @Injectable({
//   providedIn: 'root',
// })
// export class MetaMaskService {
//   private web3: Web3;

//   constructor() {
//     // Crea una instancia de web3
//     this.web3 = new Web3(Web3.givenProvider);
//   }

//   getAccountAddress(): string | null {
//     // Verifica si MetaMask está instalado y hay una cuenta logueada
//     if (this.web3.givenProvider && this.web3.givenProvider.selectedAddress) {
//       return this.web3.givenProvider.selectedAddress;
//     } else {
//       console.error('MetaMask no está instalado o no hay una cuenta logueada');
//       return null;
//     }
//   }
// }

import { Injectable } from '@angular/core';
import Web3 from 'web3';
import detectEthereumProvider from '@metamask/detect-provider';

@Injectable({
  providedIn: 'root',
})
export class MetaMaskService {
  private web3: Web3;
  private currentNetwork: string;
  private currentContract: string;

  constructor() {
    // Crea una instancia de web3
    this.web3 = new Web3(Web3.givenProvider);
    this.getNetworkId();
    this.currentNetwork = '';
    this.currentContract = '';
  }

  public initialize(): void {
    this.checkNetworkAndContract();
    this.subscribeToNetworkChanges();
    // this.connectToMetaMask();
  }

  // private connectToMetaMask(): void {
  //   detectEthereumProvider().then((provider: any) => {
  //     if (provider) {
  //       this.web3 = new Web3(provider);
  //       // Aquí puedes realizar cualquier operación adicional con MetaMask
  //     } else {
  //       console.error('MetaMask no está instalado o no se puede acceder a él');
  //     }
  //   });
  // }

  private checkNetworkAndContract(): void {
    this.web3.eth.net.getId().then((networkId: number) => {
      this.currentNetwork = networkId.toString();
      if (!this.isValidContractForNetwork(networkId.toString())) {
        this.showWarningAndRedirect();
      }
    });
  }

  private subscribeToNetworkChanges(): void {
    this.web3.givenProvider.on('accountsChanged', () => {
      const address = this.getAccountAddress();
      if (!address) {
        this.showWarningAndRedirect();
      }
    });

    this.web3.givenProvider.on('chainChanged', (networkId: string) => {
      this.currentNetwork = networkId;
      if (!this.isValidContractForNetwork(networkId)) {
        this.showWarningAndRedirect();
      }
    });
  }
  getNetworkId(): void {
    this.web3.eth.net
      .getId()
      .then((networkId: number) => {
        this.currentNetwork = networkId.toString();
        console.log('Network ID:', this.currentNetwork); // Imprimir en consola
      })
      .catch((error: any) => {
        console.error('Error al obtener el Network ID:', error);
      });
  }

  getAccountAddress(): string | null {
    if (this.web3.givenProvider && this.web3.givenProvider.selectedAddress) {
      return this.web3.givenProvider.selectedAddress;
    } else {
      console.error('MetaMask no está instalado o no hay una cuenta logueada');
      return null;
    }
  }

  private isValidContractForNetwork(networkId: string): boolean {
    // Verificar el networkId y el contrato actual
    if (networkId === '137' && this.currentContract === '0x123abc...') {
      return true; // El contrato es válido en la red Ethereum mainnet
    } else if (
      networkId === '80001 ' &&
      this.currentContract === '0x456def...'
    ) {
      return true; // El contrato es válido en la red Ropsten
    } else {
      return false; // El contrato no es válido en el networkId actual
    }
  }

  private showWarningAndRedirect(): void {
    // Implementa la lógica para mostrar una advertencia al usuario y redirigirlo a la página principal
  }
}