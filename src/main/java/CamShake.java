public class CamShake {
    private double time = 0;
    private double intensity = 0;
    private double duration = 0;
    private double elapsed = 0;
    private double timeScale = 0;
    protected double x, y;

    public CamShake() {
        x = 0;
        y = 0;
    }

    public void shake(double intensity, int duration, double timeScale) {
        this.intensity = intensity;
        this.duration = duration;
        this.timeScale = timeScale;
        this.time = 0.1;
        this.elapsed = 0; // ← reset so it always plays from the start
    }

    public void update() {
        if (elapsed < duration) {
            double falloff = 1.0 - (elapsed * timeScale / duration);
            falloff = Math.max(0, falloff);
            falloff = falloff * falloff;
            
            x = Math.sin(time * 37.0) * 0.6 + Math.sin(time * 79.0) * 0.3 + Math.sin(time * 131.0) * 0.1;
            y = Math.sin(time * 53.0) * 0.6 + Math.sin(time * 97.0) * 0.3 + Math.sin(time * 149.0) * 0.1;

            x *= intensity * falloff; // ← uncommented
            y *= intensity * falloff; // ← uncommented

            time += timeScale;
            elapsed++;
            
        } else {
            x = 0; // ← snap back to 0 when done
            y = 0;
        }
    }
}