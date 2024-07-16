/* eslint-disable @typescript-eslint/no-inferrable-types */
/* eslint-disable @angular-eslint/no-input-rename */
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { NgIf } from '@angular/common';

@Component({
    selector: 'app-form-buttons',
    templateUrl: './form-buttons.component.html',
    styleUrls: ['./form-buttons.component.css'],
    standalone: true,
    imports: [NgIf]
})
export class FormButtonsComponent {
  @Input('send-disabled') sendDisabled: boolean | null = false;
  @Input('send-text') sendText: string = 'enviar';
  @Input('cancel-text') cancelText: string = 'volver';
  @Input('delete-text') deleteText: string = 'borrar';
  @Input('delete-visible') deleteVisible: boolean = true;
  @Output() send: EventEmitter<null> = new EventEmitter<null>();
  @Output() cancel: EventEmitter<null> = new EventEmitter<null>();
  @Output() delete: EventEmitter<null> = new EventEmitter<null>();

  get hasSend(): boolean { return this.send.observed; }
  get hasCancel(): boolean { return this.cancel.observed; }
  get hasDelete(): boolean { return this.delete.observed && this.deleteVisible; }
}

