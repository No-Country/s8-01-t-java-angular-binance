import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'numberShortener',
})
export class NumberShortenerPipe implements PipeTransform {
  transform(value: number, ...args: unknown[]): unknown {
    let suffix = '';
    let shortValue = value;
    let language = 'es-ES';

    if (value >= 1000000000) {
      shortValue = value / 1000000;
      suffix = 'M';
    }

    return Math.floor(shortValue).toLocaleString(language) + suffix;
  }
}
