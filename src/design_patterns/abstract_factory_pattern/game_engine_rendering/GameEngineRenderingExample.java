package design_patterns.abstract_factory_pattern.game_engine_rendering;

// Abstract Factory
interface RenderingFactory {
    Renderer createRenderer();
    TextureLoader createTextureLoader();
}

// Concrete Factories
class DirectXFactory implements RenderingFactory {
    public Renderer createRenderer() { return new DirectXRenderer(); }
    public TextureLoader createTextureLoader() { return new DirectXTextureLoader(); }
}
class OpenGLFactory implements RenderingFactory {
    public Renderer createRenderer() { return new OpenGLRenderer(); }
    public TextureLoader createTextureLoader() { return new OpenGLTextureLoader(); }
}

// Abstract Products
interface Renderer { void render(); }
interface TextureLoader { void loadTexture(String file); }

// Concrete Products
class DirectXRenderer implements Renderer {
    public void render() { System.out.println("Rendering with DirectX"); }
}
class OpenGLRenderer implements Renderer {
    public void render() { System.out.println("Rendering with OpenGL"); }
}
class DirectXTextureLoader implements TextureLoader {
    public void loadTexture(String file) { System.out.println("Loading texture with DirectX: " + file); }
}
class OpenGLTextureLoader implements TextureLoader {
    public void loadTexture(String file) { System.out.println("Loading texture with OpenGL: " + file); }
}

// Client
public class GameEngineRenderingExample {
    public static void main(String[] args) {
        // Use case: Switch rendering backend based on platform
        RenderingFactory factory = getFactory("OpenGL");
        Renderer renderer = factory.createRenderer();
        TextureLoader loader = factory.createTextureLoader();
        renderer.render();
        loader.loadTexture("hero.png");
    }
    static RenderingFactory getFactory(String backend) {
        if (backend.equalsIgnoreCase("DirectX")) return new DirectXFactory();
        else return new OpenGLFactory();
    }
}
// Use case: Easily add new rendering backends by creating new factories and products. 