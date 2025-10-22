import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServersList } from './servers-list';

describe('ServersList', () => {
  let component: ServersList;
  let fixture: ComponentFixture<ServersList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ServersList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ServersList);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
