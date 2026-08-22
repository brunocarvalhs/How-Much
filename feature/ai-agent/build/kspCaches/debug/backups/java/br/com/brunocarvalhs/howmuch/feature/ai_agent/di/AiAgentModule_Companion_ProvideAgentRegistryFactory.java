package br.com.brunocarvalhs.howmuch.feature.ai_agent.di;

import br.com.brunocarvalhs.howmuch.core.ai.registry.AgentRegistry;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class AiAgentModule_Companion_ProvideAgentRegistryFactory implements Factory<AgentRegistry> {
  @Override
  public AgentRegistry get() {
    return provideAgentRegistry();
  }

  public static AiAgentModule_Companion_ProvideAgentRegistryFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AgentRegistry provideAgentRegistry() {
    return Preconditions.checkNotNullFromProvides(AiAgentModule.Companion.provideAgentRegistry());
  }

  private static final class InstanceHolder {
    static final AiAgentModule_Companion_ProvideAgentRegistryFactory INSTANCE = new AiAgentModule_Companion_ProvideAgentRegistryFactory();
  }
}
