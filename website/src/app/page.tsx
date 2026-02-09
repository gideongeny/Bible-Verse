"use client";

import Image from "next/image";
import { useState, useEffect } from "react";

export default function Home() {
  const [scrolled, setScrolled] = useState(false);

  useEffect(() => {
    const handleScroll = () => setScrolled(window.scrollY > 50);
    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, []);

  const features = [
    {
      title: "Seamless Reading",
      description: "Access KJV, ASV, and more with a focus on readability and speed.",
      icon: (
        <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 6.253v13m0-13C10.832 5.477 9.246 5 7.5 5S4.168 5.477 3 6.253v13C4.168 18.477 5.754 18 7.5 18s3.332.477 4.5 1.253m0-13C13.168 5.477 14.754 5 16.5 5c1.747 0 3.332.477 4.5 1.253v13C19.832 18.477 18.247 18 16.5 18c-1.746 0-3.332.477-4.5 1.253" />
        </svg>
      ),
    },
    {
      title: "Personal Insights",
      description: "Capture your spiritual journey with detailed, organized notes.",
      icon: (
        <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z" />
        </svg>
      ),
    },
    {
      title: "Image Verse Sharing",
      description: "Create and share beautiful scripture images with customized backgrounds.",
      icon: (
        <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z" />
        </svg>
      ),
    },
    {
      title: "Dynamic Themes",
      description: "Optimized for Light and Dark modes with automatic theme switching.",
      icon: (
        <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M20.354 15.354A9 9 0 018.646 3.646 9.003 9.003 0 0012 21a9.003 9.003 0 008.354-5.646z" />
        </svg>
      ),
    },
  ];

  const screenshots = [
    { url: "https://user-images.githubusercontent.com/1130895/116777602-ba87bd00-aa22-11eb-8852-14079a123f7b.png", title: "Home" },
    { url: "https://user-images.githubusercontent.com/1130895/116777778-15b9af80-aa23-11eb-91e3-6328d3f324aa.png", title: "Dark Mode" },
    { url: "https://user-images.githubusercontent.com/1130895/125149641-0031c780-e0ef-11eb-9ab6-b3acf7096457.png", title: "Image Editor" },
    { url: "https://user-images.githubusercontent.com/1130895/125420589-863452f0-121d-4f88-b4f0-0d48c0d75e42.png", title: "Notes" },
    { url: "https://user-images.githubusercontent.com/1130895/125420833-05d9521f-81cf-4b01-b797-fe477f919986.png", title: "Bookmarks" },
    { url: "https://user-images.githubusercontent.com/1130895/125421075-ffcb1adf-f3bb-47ce-9150-8e786f98ff61.png", title: "More Actions" },
  ];

  return (
    <div className="min-h-screen bg-primary selection:bg-accent/30">
      {/* Navigation */}
      <nav className={`fixed top-0 w-full z-50 transition-all duration-300 ${scrolled ? "glass py-4 shadow-xl" : "bg-transparent py-6"}`}>
        <div className="container mx-auto px-6 flex justify-between items-center">
          <div className="flex items-center gap-3">
            <Image src="/app-icon.png" alt="Logo" width={40} height={40} className="rounded-lg shadow-lg border border-accent/20" />
            <span className="text-xl font-bold tracking-tight text-gradient">Simple Bible</span>
          </div>
          <div className="hidden md:flex gap-8 text-sm font-medium">
            <a href="#features" className="hover:text-accent transition-colors">Features</a>
            <a href="#preview" className="hover:text-accent transition-colors">Preview</a>
            <a href="https://github.com/gideongeny/Bible-Verse" target="_blank" className="hover:text-accent transition-colors">GitHub</a>
          </div>
          <a href="#" className="bg-accent text-primary px-5 py-2 rounded-full text-sm font-bold hover:scale-105 active:scale-95 transition-all shadow-lg shadow-accent/20">
            Download App
          </a>
        </div>
      </nav>

      {/* Hero Section */}
      <section className="relative pt-32 pb-20 overflow-hidden">
        {/* Background glow */}
        <div className="absolute top-0 right-0 -translate-y-1/2 translate-x-1/2 w-[600px] h-[600px] bg-accent/5 rounded-full blur-[120px]" />
        <div className="absolute bottom-0 left-0 translate-y-1/2 -translate-x-1/2 w-[500px] h-[500px] bg-primary-light/20 rounded-full blur-[100px]" />

        <div className="container mx-auto px-6 flex flex-col lg:flex-row items-center gap-16">
          <div className="flex-1 text-center lg:text-left space-y-8 z-10">
            <div className="inline-block px-4 py-1.5 rounded-full glass text-accent text-xs font-bold tracking-widest uppercase mb-4 animate-fade-in-up">
              World Class Experience
            </div>
            <h1 className="text-5xl md:text-7xl font-bold leading-tight tracking-tight text-gradient">
              Read, Reflect, & <br /> <span className="text-white">Share Truth</span>
            </h1>
            <p className="text-lg text-white/60 max-w-xl mx-auto lg:mx-0 leading-relaxed">
              Simple Bible is a high-performance Android application designed for a distraction-free scripture reading experience. Beyond reading, it empowers your spiritual journey.
            </p>
            <div className="flex flex-col sm:flex-row gap-4 justify-center lg:justify-start">
              <button className="bg-accent text-primary px-8 py-4 rounded-2xl font-bold shadow-2xl shadow-accent/20 hover:-translate-y-1 active:translate-y-0 transition-transform">
                Get Started Free
              </button>
              <button className="glass px-8 py-4 rounded-2xl font-bold border border-white/10 hover:bg-white/5 transition-colors">
                View on GitHub
              </button>
            </div>
            <div className="flex items-center justify-center lg:justify-start gap-4 pt-4">
              <div className="flex -space-x-3">
                {[1, 2, 3, 4].map((i) => (
                  <div key={i} className="w-10 h-10 rounded-full border-2 border-primary bg-primary-light flex items-center justify-center text-[10px] font-bold">
                    User
                  </div>
                ))}
              </div>
              <p className="text-sm text-white/40"><span className="text-white font-semibold">1,000+</span> bible lovers already joined</p>
            </div>
          </div>

          <div className="flex-1 relative perspective-1000">
            <div className="relative z-10 animate-float preserve-3d">
              <div className="w-[280px] md:w-[320px] mx-auto rounded-[3rem] p-3 glass border-white/20 shadow-[0_50px_100px_-20px_rgba(0,0,0,0.5)]">
                <div className="overflow-hidden rounded-[2.5rem] bg-primary-dark aspect-[9/19]">
                  <Image
                    src="https://user-images.githubusercontent.com/1130895/116777602-ba87bd00-aa22-11eb-8852-14079a123f7b.png"
                    alt="App Screenshot"
                    width={320}
                    height={640}
                    className="w-full h-full object-cover"
                  />
                </div>
              </div>
            </div>
            {/* Background decorative elements */}
            <div className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[400px] h-[400px] border border-accent/20 rounded-full animate-ping opacity-20" style={{ animationDuration: '4s' }} />
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section id="features" className="py-24 relative overflow-hidden">
        <div className="container mx-auto px-6">
          <div className="text-center space-y-4 mb-20">
            <h2 className="text-4xl font-bold">Premium Features</h2>
            <p className="text-white/60 max-w-2xl mx-auto">Everything you need for a deep and meaningful connection with the Word.</p>
          </div>
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
            {features.map((f, i) => (
              <div key={i} className="glass p-8 rounded-3xl hover:-translate-y-2 transition-transform group">
                <div className="w-14 h-14 rounded-2xl bg-accent/10 flex items-center justify-center text-accent mb-6 group-hover:bg-accent group-hover:text-primary transition-colors">
                  {f.icon}
                </div>
                <h3 className="text-xl font-bold mb-3">{f.title}</h3>
                <p className="text-sm text-white/50 leading-relaxed">{f.description}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Preview Section */}
      <section id="preview" className="py-24 bg-primary-light/10">
        <div className="container mx-auto px-6 text-center">
          <div className="space-y-4 mb-16">
            <h2 className="text-4xl font-bold">Beautifully Designed</h2>
            <p className="text-white/60 max-w-2xl mx-auto">A modern interface that honors the sacred text with clarity and elegance.</p>
          </div>

          <div className="flex overflow-x-auto gap-8 pb-12 snap-x mask-fade no-scrollbar">
            {screenshots.map((s, i) => (
              <div key={i} className="flex-none w-[260px] snap-center">
                <div className="glass p-3 rounded-[2.5rem] shadow-2xl mb-4 group overflow-hidden">
                  <div className="rounded-[2rem] overflow-hidden aspect-[9/19]">
                    <Image src={s.url} alt={s.title} width={260} height={520} className="w-full h-full object-cover grayscale hover:grayscale-0 transition-all duration-500 scale-105 group-hover:scale-100" />
                  </div>
                </div>
                <p className="text-sm font-medium text-white/40">{s.title}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="py-24">
        <div className="container mx-auto px-6">
          <div className="glass rounded-[4rem] p-12 md:p-20 text-center relative overflow-hidden">
            <div className="absolute top-0 right-0 w-[300px] h-[300px] bg-accent/10 rounded-full blur-[80px]" />
            <div className="z-10 relative space-y-8">
              <h2 className="text-4xl md:text-5xl font-bold text-gradient">Start Your Journey Today</h2>
              <p className="text-lg text-white/60 max-w-xl mx-auto">Download the Simple Bible app now and carry the living Word in your pocket with grace and style.</p>
              <div className="flex flex-wrap justify-center gap-4">
                <button className="bg-white text-primary px-8 py-4 rounded-2xl font-bold hover:bg-white/90 transition-colors">Google Play</button>
                <button className="bg-accent text-primary px-8 py-4 rounded-2xl font-bold hover:bg-accent/90 transition-colors">Direct APK</button>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Footer */}
      <footer className="py-12 border-t border-white/5">
        <div className="container mx-auto px-6 flex flex-col md:flex-row justify-between items-center gap-8">
          <div className="flex items-center gap-3">
            <Image src="/app-icon.png" alt="Logo" width={32} height={32} />
            <span className="font-bold tracking-tight">Simple Bible</span>
          </div>
          <p className="text-sm text-white/30 text-center">© 2026 Bible Verse App. All rights reserved. Made with ❤️ for the community.</p>
          <div className="flex gap-6 text-white/30 text-sm">
            <a href="#" className="hover:text-white transition-colors">Privacy Policy</a>
            <a href="#" className="hover:text-white transition-colors">Terms of Service</a>
          </div>
        </div>
      </footer>

      <style jsx global>{`
        .no-scrollbar::-webkit-scrollbar { display: none; }
        .no-scrollbar { -ms-overflow-style: none; scrollbar-width: none; }
      `}</style>
    </div>
  );
}
