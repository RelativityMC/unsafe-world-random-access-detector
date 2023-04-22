package com.ishland.uwrad;

import com.ishland.uwrad.common.Config;
import com.ishland.uwrad.common.debug.SMAPPool;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.mixin.transformer.IMixinTransformer;
import org.spongepowered.asm.mixin.transformer.ext.Extensions;
import org.spongepowered.asm.mixin.transformer.ext.IExtension;
import org.spongepowered.asm.mixin.transformer.ext.ITargetClassContext;

import java.util.List;
import java.util.Set;

public class TheMixinPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {
        Config.init();
        if (MixinEnvironment.getCurrentEnvironment().getActiveTransformer() instanceof IMixinTransformer transformer &&
            transformer.getExtensions() instanceof Extensions extensions) {
            extensions.add(new IExtension() {
                @Override
                public boolean checkActive(MixinEnvironment environment) {
                    return true;
                }

                @Override
                public void preApply(ITargetClassContext context) {

                }

                @Override
                public void postApply(ITargetClassContext context) {

                }

                @Override
                public void export(MixinEnvironment env, String name, boolean force, ClassNode classNode) {
                    SMAPPool.put(name, classNode);
                }
            });
        } else {
            System.err.println("Failed to initialize SMAP parser for safe world random access, mod information for mixin injected methods will not be available");
        }
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
//        return ClassInfo.forName("com.ishland.c2me.fixes.worldgen.threading_issues.common.CheckedThreadLocalRandom") == null;
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
