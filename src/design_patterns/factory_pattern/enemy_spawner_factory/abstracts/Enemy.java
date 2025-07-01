package design_patterns.factory_pattern.enemy_spawner_factory.abstracts;

// Product Interface
public interface Enemy {
    void attack();
    void takeDamage(int damage);
    String getName();
    int getHealth();
}