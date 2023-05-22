import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'numberShortener'
})
export class NumberShortenerPipe implements PipeTransform {

  transform(value: number, ...args: unknown[]): unknown {
    let suffix = '';
    let shortValue = value;

    if (value >= 1000000000) {
      shortValue = value / 1000000000;
      suffix = 'B';
    } else if (value >= 1000000) {
      shortValue = value / 1000000;
      suffix = 'M';
    }
    
    return shortValue + suffix;
  }

}
