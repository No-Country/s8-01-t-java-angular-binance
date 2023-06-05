import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { create } from 'ipfs-http-client';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class IpfsService {
  client: any;
  projectId = environment.ipfsKey;
  projectSecret = environment.ipfsKeyS;
  baseUrl = environment.ipfs;
  images: any[] = [];

  httpHeader = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor() {
    const auth = 'Basic ' + btoa(this.projectId + ':' + this.projectSecret);
    this.client = create({
      url: this.baseUrl,
      host: 'ipfs.infura.io',
      port: 5001,
      protocol: 'https',
      headers: {
        authorization: auth,
      },
    });
  }

  // public uploadFile(event: any): Observable<any> {
  //   const file = event;
  //   return from(this.client.add(file)).pipe(
  //     map((result: any) => {
  //       const { cid, path, size, mtime } = result;
  //       const images = [
  //         ...this.images,
  //         {
  //           cid: cid.toString(),
  //           path: path,
  //           size: size,
  //           mtime: mtime,
  //         },
  //       ];
  //       return images;
  //     })
  //   );
  // }
  // public uploadFile(event: any): Observable<string> {
  //   const file = event;
  //   return from(this.client.add(file)).pipe(
  //     map((result: any) => {
  //       const { cid } = result;
  //       return cid.toString();
  //     })
  //   );
  // }

  public async uploadFile(data: any): Promise<string> {
    let url = '';

    try {
      const added = await this.client.add(data);
      url = `https://nocountry.infura-ipfs.io/ipfs/${added.path}`;
    } catch (error) {
      console.log(error);
    }

    return url;
  }

  public async buyNft(data: any): Promise<string> {
    let url = '';

    try {
      const added = await this.client.add(data);
      url = `${added.path}`;
    } catch (error) {
      console.log(error);
    }

    return url;
  }
}
