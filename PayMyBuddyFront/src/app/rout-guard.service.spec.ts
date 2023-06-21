import { TestBed } from '@angular/core/testing';

import { RoutguardService } from './rout-guard.service';

describe('RoutGuardService', () => {
  let service: RoutguardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RoutguardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
