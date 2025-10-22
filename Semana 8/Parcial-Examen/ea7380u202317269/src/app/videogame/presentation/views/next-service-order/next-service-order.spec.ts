import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NextServiceOrder } from './next-service-order';

describe('NextServiceOrder', () => {
  let component: NextServiceOrder;
  let fixture: ComponentFixture<NextServiceOrder>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NextServiceOrder]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NextServiceOrder);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
