
export class AirData  {
  data: Object;

  constructor(data: Object) {
    this.data = data;
  }

  public getData(): Object {
    return this.data
  }

  public setData(data: Object) {
    this.data = data;
  }
}
