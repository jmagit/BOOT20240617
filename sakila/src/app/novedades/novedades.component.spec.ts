import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NovedadesComponent } from './novedades.component';

describe('NovedadesComponent', () => {
  let component: NovedadesComponent;
  let fixture: ComponentFixture<NovedadesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [NovedadesComponent]
    });
    fixture = TestBed.createComponent(NovedadesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
