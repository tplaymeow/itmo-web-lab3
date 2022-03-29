class CanvasView {
    constructor(element) {
        this.element = element;
        this._width = 600;
        this._height = 600;
    }

    render(radius) {
        const ctx = this.element.getContext('2d');
        const center = { x: this._width / 2, y: this._height / 2 }
        const scaleFactor = this._width / 10

        ctx.canvas.height = this._height;
        ctx.canvas.width = this._width;

        this._drawBoard(ctx);

        ctx.fillStyle = '#3333ff';
        ctx.globalAlpha = 0.8;
        ctx.strokeStyle = "#000055";
        ctx.lineWidth = 2;

        ctx.beginPath();

        ctx.moveTo(center.x, center.y);
        ctx.lineTo(center.x - radius * scaleFactor, center.y);
        ctx.lineTo(center.x, center.y - 0.5 * radius * scaleFactor);
        ctx.lineTo(center.x, center.y - radius * scaleFactor);
        ctx.lineTo(center.x + 0.5 * radius * scaleFactor, center.y - radius * scaleFactor);
        ctx.lineTo(center.x + 0.5 * radius * scaleFactor, center.y);
        ctx.arc(center.x, center.y, 0.5 * radius * scaleFactor, 0, Math.PI / 2);

        ctx.closePath();

        ctx.fill();
        ctx.stroke();
    }

    set onClickCanvas(value) {
        this.element.addEventListener('click', e => {
            const position = this._getCursorPosition(e);
            const coordinate = this._positionToCoordinate(position);
            value(coordinate);
        })
    }

    set isBlurEnabled(isEnabled) {
        this.element.style.filter = isEnabled
            ? 'blur(5px)'
            : 'blur(0px)';
    }

    drawDot(coordinate, color) {
        const ctx = this.element.getContext('2d');
        const position = this._coordinateToPosition(coordinate);
        const radius = 5;

        ctx.fillStyle = color;
        ctx.beginPath();
        ctx.arc(
            position.x,
            position.y,
            radius,
            0,
            2 * Math.PI);
        ctx.fill();
        ctx.closePath();
    }

    _getCursorPosition(event) {
        const rect = this.element.getBoundingClientRect();
        const x = event.clientX - rect.left;
        const y = event.clientY - rect.top;
        return { x: x, y: y };
    }

    // - Drawing

    _drawBoard(context) {
        const width = this._width;
        const height = this._height;
        for (let x = width / 10; x < width; x += width / 10) {
            context.moveTo(x, 0);
            context.lineTo(x, height);
        }
        for (let y = height / 10; y < height; y += height / 10) {
            context.moveTo(0, y);
            context.lineTo(width, y);
        }
        context.stroke();
    }

    // - Convertors

    _positionToCoordinate(position) {
        const scaleFactor = this._width / 10;
        return  {
            x: (position.x - this._width / 2) / scaleFactor,
            y: (position.y - this._height / 2) / -scaleFactor
        };
    }

    _coordinateToPosition(coordinate) {
        const scaleFactor = this._width / 10;
        return {
            x: (coordinate.x * scaleFactor) + this._width / 2,
            y: (coordinate.y * -scaleFactor) + this._height / 2
        }
    }
}