class Progress {
  private progressBar: HTMLElement | null | undefined
  private _min: number
  private _max: number
  private _value: number
  constructor(min: number = 0, max: number = 100, value: number = min) {
    this._max = max
    this._min = min
    this._value = value
  }

  bind() {
    if (!this.progressBar) {
      this.progressBar = document.getElementById('progress-bar')
    }
  }

  init() {
    this.bind()
    if (this.progressBar) {
      this.progressBar.style.display = 'inline-block'
      this.progressBar.setAttribute('value', this._min.toString())
      this.progressBar.setAttribute('max', this._max.toString())
    }
  }
  start() {
    this.init()
    if (this.progressBar) {
      const pb = this.progressBar
      const interval = setInterval(() => {
        if (this._value <= this._max * 0.6) {
          this._value += 10
          pb.setAttribute('value', this._value.toString())
        } else if (this._value <= this._max * 0.9) {
          this._value += 5
          pb.setAttribute('value', this._value.toString())
        } else {
          clearInterval(interval)
        }
      }, 100)
    }
  }
  done() {
    this.bind()
    if (this.progressBar) {
      this.progressBar.setAttribute('value', this._max.toString())
      const am = this.progressBar.animate([{ opacity: 1 }, { opacity: 0 }], {
        duration: 100,
      })
      am.onfinish = () => {
        if (this.progressBar) {
          this.progressBar.style.display = 'none'
        }
      }
    }
  }
}

export const progress = new Progress()

export default Progress
