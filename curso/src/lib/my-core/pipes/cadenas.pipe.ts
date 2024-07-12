import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'elipsis', standalone: true
})
export class ElipsisPipe implements PipeTransform {
  transform(value: string, maxlen: number) {
    return (!maxlen || maxlen < 2 || !value || value.length <= maxlen) ? value : (value.substr(0, maxlen - 1) + '\u2026');
  }
}
@Pipe({
  name: 'capitalize', standalone: true
})
export class CapitalizePipe implements PipeTransform {
  transform(value: string) {
    return value?.toString().toLowerCase().split(".").map(frase => frase.trim())
      .map((frase, index, array) => frase.length === 0  ? (array.length > 1 && index + 1 < array.length  ? (array[index + 1] ==='' ? '.' : '. '): '')
       : frase.charAt(0)?.toUpperCase() + frase?.substring(1) + (array.length > 1 && index + 1 < array.length ? (array[index + 1] ==='' ? '.' : '. '):''))
      .join('').trim()
    // return value?.charAt(0)?.toUpperCase() + value?.substring(1)?.toLowerCase();
  }
}


export const PIPES_CADENAS = [ ElipsisPipe, CapitalizePipe ]
